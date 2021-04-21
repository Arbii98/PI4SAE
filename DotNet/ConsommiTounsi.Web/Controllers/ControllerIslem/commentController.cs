using ConsommiTounsi.Domaine.entities;
using ConsommiTounsi.Domaine.entities.entities_Forum;
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

        // GET: Commentaire/Create
        public ActionResult Create(int idPub, string description)
        {
            userc = new User(1, "Islem", "");
            Session["userConnected"] = userc;
            HttpClient client = new HttpClient();
            HttpRequestMessage requestMessage = new HttpRequestMessage(HttpMethod.Post, "http://localhost:8081/SpringMVC/servlet/Comment");

            string json = new JavaScriptSerializer().Serialize(new
            {

                // user = new { id = userc.id }
                commentPk= new {
                idPub= idPub,
            idUser= userc.id,
          
                },
        description = description

            }); ;

            requestMessage.Content = new StringContent(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.SendAsync(requestMessage).GetAwaiter().GetResult();
           return RedirectToAction("Index", "Publication");
            //return View();
        }

        // POST: Commentaire/Create
        [HttpPost]
        public ActionResult Create(FormCollection collection)
        {

            try
            {
                // TODO: Add insert logic here

                return View();
            }
            catch
            {
                return View();
            }
        }
    }
}