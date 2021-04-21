using ConsommiTounsi.Domaine.entities;
using ConsommiTounsi.Domaine.entities.entities_Forum;
using ConsommiTounsi.Web.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Web;
using System.Web.Mvc;
using System.Web.Script.Serialization;

namespace ConsommiTounsi.Web.Controllers.ControllerIslem
{
    public class PublicationController : Controller
    {
        HttpClient httpClient;
        string baseAddress;
        User userc;
        public ActionResult Index()
        {
            userc = new User(1, "Islem", "");
            Session["userConnected"] = userc;

            // GET: Publishes
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage responce = Client.GetAsync("publication/getAllPublishByTopComment").Result;

            if (responce.IsSuccessStatusCode)
            {
                IEnumerable<Publication> lstpub = responce.Content.ReadAsAsync<IEnumerable<Publication>>().Result;
                ViewBag.result = lstpub;
                ViewBag.user = Session["userConnected"] as User;
            }
            else
            {
                ViewBag.pub = "error";
            }

            return View();
        }
        // GET: Publication/Delete/5

        public ActionResult Delete(int idPub)
        {

            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage responce = Client.DeleteAsync("publication/" + idPub).Result;
            return RedirectToAction("Index");

        }



        public ActionResult Create()
        {

            return View();
        }
        // POST: Publication/Create
        [HttpPost]
        public ActionResult Create(PublicationVm Publi)
        {


            HttpClient client = new HttpClient();

            HttpRequestMessage requestMessage = new HttpRequestMessage(HttpMethod.Post, "http://localhost:8081/SpringMVC/servlet/publication/1");
            string json = new JavaScriptSerializer().Serialize(new
            {
                title = "test",
                description = Publi.description,

            });

            requestMessage.Content = new StringContent(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.SendAsync(requestMessage).GetAwaiter().GetResult();
            return View();
        }



        [HttpPost]
        public ActionResult Edit(PublicationVm Publi, int idPub)
        {


            HttpClient client = new HttpClient();

            HttpRequestMessage requestMessage = new HttpRequestMessage(HttpMethod.Put, "http://localhost:8081/SpringMVC/servlet/publication/"+idPub);
            string json = new JavaScriptSerializer().Serialize(new
            {
                title = "test",
                description = Publi.description,

            });

            requestMessage.Content = new StringContent(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.SendAsync(requestMessage).GetAwaiter().GetResult();
            return View();
        }
        public ActionResult Edit()
        {

            return View();
        }

    }
}
    


