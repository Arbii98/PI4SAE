using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Net.Http;
using ConsommiTounsi.Domaine.Entities;
using System.Net.Http.Headers;

namespace ConsommiTounsi.Web.Controllers
{
    public class EventController : Controller
    {
        HttpClient httpClient;
        string baseAddress;
        public EventController()
        {
            baseAddress = "http://localhost:8081/SpringMVC/servlet/";
            httpClient = new HttpClient();
            httpClient.BaseAddress = new Uri(baseAddress);
            httpClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            //var _AccessToken = Session[" AccessToken "];
            //httpClient.DefaultRequestHeaders.Add(" Authorization ", String.Format(" Bearer {0} "));
        }


        public ActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Create(Event eve)
        {
            try
            {
                eve.Cout = 100;
                eve.Description = "Ya 3fat";
                eve.Titre = "zzzzzzzzzzzzzz";
               /* HttpClient Client = new HttpClient();
                Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
                Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
                var APIResponse = Client.PostAsJsonAsync<Event>(Client.BaseAddress + "addEvent",eve).ContinueWith(postTask => postTask.Result.EnsureSuccessStatusCode());
               */
                var APIResponse = httpClient.PostAsJsonAsync<Event>(baseAddress + "addEvent",eve).ContinueWith(postTask => postTask.Result.EnsureSuccessStatusCode());

            }
            catch(Exception e){}
            return RedirectToAction("Index");
        }


        // GET: Event
        public ActionResult Index()
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("getUpcomingEvents").Result;
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

        public ActionResult Historique()
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

        //[HttpPost]
        public ActionResult Delete(int id)
        {
            //int id = 5;
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            var APIResponse = Client.DeleteAsync(Client.BaseAddress + "deleteEvent/"+id);
            return RedirectToAction("Index");
        }


    }
}