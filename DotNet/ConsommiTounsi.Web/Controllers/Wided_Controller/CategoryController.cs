using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;
using ConsommiTounsi.Domaine.Entities.Product;

namespace ConsommiTounsi.Web.Controllers.Product_Controller
{
    public class CategoryController : Controller
    {
        // GET: Category
        public ActionResult Index()
        {

            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage responce = Client.GetAsync("getAllCategories").Result;

            if (responce.IsSuccessStatusCode)
            {
                IEnumerable<Category> categories = responce.Content.ReadAsAsync<IEnumerable<Category>>().Result;
                ViewBag.categories = categories;
            }
            else
            {
                ViewBag.categories = "error";
            }

            return View();
        }
    }
       
}