using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Net.Http;
using ConsommiTounsi.Domaine.Entities;

namespace ConsommiTounsi.Web.Controllers
{
    public class EventController : Controller
    {


        // GET: Event
        public ActionResult Index()
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("getAllEvents").Result;
            if (response.IsSuccessStatusCode)
            {
                IEnumerable<Event> events = response.Content.ReadAsAsync<IEnumerable<Event>>().Result;
                ViewBag.events = events;
            }
            else
            {
                ViewBag.events = "error";
            }

            return View();
        }

        public ActionResult Delete()
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("getAllEvents").Result;
            if (response.IsSuccessStatusCode)
            {
                IEnumerable<Event> events = response.Content.ReadAsAsync<IEnumerable<Event>>().Result;
                ViewBag.events = events;
            }
            else
            {
                ViewBag.events = "error";
            }

            return View("~/Views/Event/Index.cshtml");
        }
    }
}