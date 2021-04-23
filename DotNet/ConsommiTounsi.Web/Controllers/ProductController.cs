using ConsommiTounsi.Domaine.Entities.Product;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web;
using System.Web.Mvc;

namespace ConsommiTounsi.Web.Controllers
{
    public class ProductController : Controller
    {

        HttpClient httpClient;
        string baseAddress;
        public ProductController()
        {
            baseAddress = "http://localhost:8081/SpringMVC/servlet/";
            httpClient = new HttpClient();
            httpClient.BaseAddress = new Uri(baseAddress);
            httpClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            //var _AccessToken = Session[" AccessToken "]) ;
            //httpClient.DefaultRequestHeaders.Add(" Authorization ", String.Format(" Bearer {0} ", _AccessToken));
        }

        // GET: Product
        public ActionResult Index()
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage responce = Client.GetAsync("getAllProducts").Result;

            if (responce.IsSuccessStatusCode)
            {
                IEnumerable<Product> products = responce.Content.ReadAsAsync<IEnumerable<Product>>().Result;
                ViewBag.products = products;
            }
            else
            {
                ViewBag.products = "error";
            }

            return View();
        }

        // GET: Product/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Product/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Product/Create
        /*[HttpPost]
        public ActionResult Create(Product product, HttpPostedFileBase file)
        {

            try
            {
                
                if (file.ContentLength > 0 && ModelState.IsValid)
                {
                    product.imageFileNameProduct = file.FileName;
                    var path = Path.Combine(Server.MapPath("~/Content/Uploads/"), file.FileName);
                    file.SaveAs(path);
                }

                var APIResponse = httpClient.PostAsJsonAsync<Product>(baseAddress + "addProduct/", product).ContinueWith(postTask => postTask.Result.EnsureSuccessStatusCode());
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }

        }*/

        [HttpPost]
        public ActionResult Create(Product p, HttpPostedFileBase file)
        {
            System.Diagnostics.Debug.WriteLine("fahdddddddfezfezfezfzeffezfd");
            try
            {
                //System.Diagnostics.Debug.WriteLine("fahdddddddd");
                
                if (file.ContentLength > 0 /*&& ModelState.IsValid*/)
                {
                    p.imageFileNameProduct = file.FileName;
                    var path = Path.Combine(Server.MapPath("~/Content/Uploads"), file.FileName);
                    file.SaveAs(path);
                    
                }

                var APIResponse = httpClient.PostAsJsonAsync<Product>(baseAddress + "addProduct/", p).ContinueWith(postTask => postTask.Result.EnsureSuccessStatusCode());
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Product/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Product/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add update logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Product/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Product/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
