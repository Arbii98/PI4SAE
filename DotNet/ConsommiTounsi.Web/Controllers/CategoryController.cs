using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;
using ConsommiTounsi.Domaine.Entities.Product;

namespace ConsommiTounsi.Web.Controllers
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

        /* [HttpDelete]
         [Route("http://localhost:8081/SpringMVC/servlet/deleteCategoryById/{idCat}")]
         public ActionResult Delete(int idCat)
         {

             Console.WriteLine("wided");



             /*HttpClient Client = new HttpClient();
             Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet");
             Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
             HttpResponseMessage responce = Client.DeleteAsync("/deleteCategoryById/" + idCat).Result;
             return RedirectToAction("Index");
             return null;

         }*/

        [HttpPost]
        public ActionResult Create(Category category)
        {
            try
            {

                //var APIResponse = HttpClient.PostAsJsonAsync<Category>("http://localhost:8081/SpringMVC/servlet" + "/category/", category).ContinueWith(postTask => postTask.Result.EnsureSuccessStatusCode());*/
                return RedirectToAction(" Index ");
            }
            catch
            {
                return View();
            }
        }

    }
}