using ConsommiTounsi.Domaine.entities;
using ConsommiTounsi.Domaine.entities.entities_Forum;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;

namespace ConsommiTounsi.Web.Controllers.ControllerIslem
{
    public class commentController : Controller
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
            HttpResponseMessage responce = Client.GetAsync("Comment/+idPub").Result;

            if (responce.IsSuccessStatusCode)
            {
                IEnumerable<Comment> lstcomment = responce.Content.ReadAsAsync<IEnumerable<Comment>>().Result;
                ViewBag.result = lstcomment;
                ViewBag.user = Session["userConnected"] as User;
            }
            else
            {
                ViewBag.pub = "error";
            }

            return View();
        }
    }
}