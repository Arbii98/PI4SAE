using ConsommiTounsi.Domaine.Entities.Product;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web;
using System.Web.Mvc;

namespace ConsommiTounsi.Web.Controllers
{
    public class BrandController : Controller
    {

        HttpClient httpClient;
        string baseAddress;
        public BrandController()
        {
            baseAddress = " http://localhost:8081/SpringMVC/servlet/";
            httpClient = new HttpClient();
            httpClient.BaseAddress = new Uri(baseAddress);
            httpClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        }


        // GET: Brand
        public ActionResult Index()
        {
            HttpResponseMessage responce = httpClient.GetAsync("getAllMarques").Result;

            if (responce.IsSuccessStatusCode)
            {
                IEnumerable<Marque> brands = responce.Content.ReadAsAsync<IEnumerable<Marque>>().Result;
                ViewBag.brands = brands;
            }
            else
            {
                ViewBag.brands = "error";
            }
            return View();
        }

        // GET: Brand/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Brand/Create
        public ActionResult Create(Marque marque)
        {
            try
            {
                var APIResponse = httpClient.PostAsJsonAsync<Marque>(baseAddress + "addMarque/", marque).ContinueWith(postTask => postTask.Result.EnsureSuccessStatusCode());
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // POST: Brand/Create
        [HttpPost]
        public ActionResult Create(FormCollection collection)
        {
            try
            {
                // TODO: Add insert logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Brand/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Brand/Edit/5
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

        // GET: Brand/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Brand/Delete/5
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
