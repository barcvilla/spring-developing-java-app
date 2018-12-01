/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.controller;
import com.packt.webstore.domain.Product;
import com.packt.webstore.service.ProductService;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase ProductContoller anotada como controller que indica a Spring crear y administrar este objeto como un spring bean
 * @author PC
 */
@Controller
@RequestMapping("market")
public class ProductController {
    /**
     * Hacemos una referencia al ProductRepository.
     * Mediante @Autowired Spring asigna internamente una referencia de la clase ProductRepositoryImpl el cual es un bean
     * administrado en el spring container (web application context)
     * al objeto productRepository
     */
    //@Autowired
    //private ProductRepository productRepository; // referencia para acceder a los objetos del dominio Product
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping("/products")
    public String list(Model model)
    {
        model.addAttribute("products", productService.getAllProducts());
        
        return "products";
    }
    
    @RequestMapping("/update/stock")
    public String updateStock(Model model)
    {
        productService.updateAllStock();
        return "redirect:/products";
    }
    
    /**
     * Si encerramos un porcion del Request Path entre llaves, le estamos diciendo al Spring MVC que el URL Template es variable
     * por ejemplo el URI: http://localhost:8080/webstore/market/products/{category} contiene una variable category
     * asignarle el valor Laptop se convierte el uri: http://localhost:8080/webstore/market/products/Laptop
     * En Spring MVC podemos utilizar la anotacion @PathVariable para obtener y leer un URI Template variable {category}
     * Spring MVC leera cualquier valor ingresado en el URI Template variable {category} y lo pasara a productCategory
     * El valor del atributo de la anotacion @PathVarible deberia ser el mismo que el nombre de la variable de la path expression
     * en el request mapping. Por ejemplo, si el expression path es "products/{category}" entonces para recuperar el
     * path variable category utilizar la anotacion asi: @PathVairable("category")
     * @param model
     * @param productCategory
     * @return 
     */
    @RequestMapping(value = "/products/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category")String productCategory)
    {
        model.addAttribute("products", productService.getProductsByCategory(productCategory));
        return "products";
    }
    
    @RequestMapping("/products/filter/{params}")
    public String getProductsByFilter(@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams, Model model)
    {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }
    
    /**
     * Como anotamos el parametro productId con la anotacion @RequestParam("id") Spring MVC intentara leer el GET request 
     * parameter con el nombre id del URL y asignara este valor al parametro productId del metodo getProductById
     * Si deseamos pasar mas de un GET request parameter, utilizamos el delimitador standard de HTTP de la sgte manera:
     * http://localhost:8080/webstore/market/product?category=laptop&price=700
     * el metodo seria: public String getProducts(@RequestParam("category") String category, @RequestParam("price") String price)
     * @param productId
     * @param model
     * @return 
     */
    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model)
    {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }
    
    /**
     * Cuando ingresamos http://localhost:8080/webstore/market/product/add se produce un GET request, asi que, Spring MVC
     * mapeara este request al metodo getAddNewProductForm()
     * dentro del metodo simplemente adjuntan un objeto de dominio Product vacio en el objeto model bajo el nombre de atributo
     * newProduct de esta forma en la pagina addproduct.jsp podemos acceder al Model objeto newProduct
     * @param model
     * @return 
     */
    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model)
    {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addproduct";
    }
    
    /**
     * La pregunta interesante es: como la variable newProduct es poblada con los datos que ingresamos en el formulario?
     * Esto es posible a la anotacion @ModelAttribute. El valor de la anotacion @ModelAttribute y el valor del atributo
     * modelAttribute del formulario son lo mismo. De esta forma Spring MVC sabe que debe asignar el objeto newProduct del
     * formulario hacia la variable newProduct del metodo processAddNewProductForm.
     * La anotacion @ModelAttribute no solo es usado para recuperar un objeto desde el Model, pero si queremos podemos incluso
     * usar la anotacion @ModelAttribute para adicionar objeto al Model. Por ejemplo podemos re-escribir nuestro metodo
     * getAddNewProductForm algo como sigue:
     * @RequestMapping(value = "/products/add", method = RequestMethod.GET)
     * public String getAddNewProductForm(@ModelAttribute("newProduct") Product newProduct)
     * {
     *      return "addproduct";
     * }
     * Como se puede ver, no se ha creado un objeto de dominio vacio Product ni se ha adjuntado al model. Todo lo que hicimos
     * fue adicionar un parametro de tipo Product y anotarlo con la anotacion @ModelAttribute, asi SPring MVC sabe que debe
     * crear un objeto de tipo Product y adjuntarlo al model bajo el nombre newProduct
     * HttpServletRequest request : 
     * @param newProduct
     * @return 
     */
    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result, HttpServletRequest request)
    {
        String[] suppressedFields = result.getSuppressedFields();
        if(suppressedFields.length > 0)
        {
            throw new RuntimeException("Attempting to bind disallowed fields " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        
        // get productImage
        MultipartFile productImage = newProduct.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        if(productImage != null && !productImage.isEmpty())
        {
            try
            {
                productImage.transferTo(new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".png"));
                System.out.println(rootDirectory + "resources\\images\\"+newProduct.getProductId()+".png");
            }
            catch(Exception ex)
            {
                throw new RuntimeException("Product Image Saving failed", ex);
            }
        }
        
        productService.addProduct(newProduct);
        return "redirect:/market/products";
    }

    /**
     * WebDataBinder extrae los datos del objeto HttpServletRequest y lo convierte a un formato de datos apropiado, lo carga
     * en un formulario backing bean. Para personalizar el comportamiento del data binding, podemos inicializar y configurar
     * el objeto WebDataBinder en nuestro objeto Controller.
     * @InitBinder: Esta anotacion designa un metodo para inicializar WebDataBinder. Actualmente estamos utilizando el objeto
     * de dominio Product como un formulario backing bean, hacer un submit puede generar una vulnerabilidad de la seguridad.
     * @param binder 
     */
    @InitBinder
    public void initialiseBinder(WebDataBinder binder)
    {
        /**
         * Mientras que al adicionar un nuevo producto nosotros enlazamos cada campo del Product domain en el formulario,
         * pero si somos estrictos no tiene sentido espeificar unitsInOrder y discontinued durante la adicion de un nuevo
         * producto ya que nadie hace un pedido antes de adicionar un producto. 
         */
        binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category", "unitsInStock", "condition", "productImage");
    }
}
