package ru.zinnur.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zinnur.service.forms.ProductForm;
import ru.zinnur.service.models.Manufacturer;
import ru.zinnur.service.models.Product;
import ru.zinnur.service.repositories.ManufacturerRepository;
import ru.zinnur.service.repositories.ProductsRepository;
import ru.zinnur.service.security.details.UserDetailsImpl;
import ru.zinnur.service.transfer.UserDto;

import java.util.Optional;

import static ru.zinnur.service.transfer.UserDto.from;

@Controller
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    //(READ)
    @GetMapping("/products")
    public String getProductsPage(ModelMap model, Authentication authentication) {
        if (authentication != null) {
            UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
            UserDto user = from(details.getUser());
            model.addAttribute("user", user);
        }
        model.addAttribute("productsFromServer", productsRepository.findAll());
        return "products";
    }

    //создать новый товар и сохранить в БД (CREATE)
    @PostMapping("/products")
    public String saveProduct(ProductForm productForm) {

        //сначала проверить, есть ли уже товар с таким названием
        Optional<Product> productCandidate = productsRepository.findOneByName(productForm.getName());

        //если нет такого, создать, иначе ничего не делаем
        if (!productCandidate.isPresent()) {
            //пробуем найти производителя по имени
            Optional<Manufacturer> manufacturerCandidate = manufacturerRepository.findOneByManufacturerName(productForm.getManufacturer());
            Manufacturer manufacturer;
            //если такого нет, создать производителя
            if (!manufacturerCandidate.isPresent()) {
                manufacturer = Manufacturer.builder()
                        .manufacturerName(productForm.getManufacturer())
                        .build();
                manufacturerRepository.save(manufacturer);
            } else {
                manufacturer = manufacturerCandidate.get();
            }
            //создать товар с этим производителем, найденным ранее или только что созданным
            Product product = Product.builder()
                    .name(productForm.getName())
                    .manufacturer(manufacturer)
                    .count(productForm.getCount())
                    .cost(productForm.getCost())
                    .build();

            //сохранить товар в БД
            productsRepository.save(product);
        }
        return "redirect:products";
    }

    //удалить товар по id (DELETE)
    @PostMapping(path = "/products", params = "del")
    public String deleteProduct(ProductForm productForm) {

        productsRepository.delete(productForm.getId());
        return "redirect:products";
    }

    //обновление стоимости (UPDATE)
    @PostMapping(path = "/products", params = "upd")
    public String update(ProductForm productForm) {

        Product product;
        //делаем через save, так как update не выполняется
        Optional<Product> productCandidate = productsRepository.findOneByName(productForm.getName());
        //обновить, если товар с таким именем существует, иначе ничего не делаем
        if (productCandidate.isPresent()) {

            product = Product.builder()
                    //id и другое новому объекту присвоить из найденной книги
                    .id(productCandidate.get().getId())
                    .name(productCandidate.get().getName())
                    .manufacturer(productCandidate.get().getManufacturer())
                    .count(productCandidate.get().getCount())
                    //новая стоимость из формы
                    .cost(productForm.getCost())
                    .build();
            productsRepository.save(product);
        }
        return "redirect:products";
    }
}