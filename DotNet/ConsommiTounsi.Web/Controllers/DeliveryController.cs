using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Net.Http;
using ConsommiTounsi.Domaine.Entities;
using ConsommiTounsi.Domaine.Entities.Stat;
using Newtonsoft.Json;

namespace ConsommiTounsi.Web.Controllers
{
    public class DeliveryController : Controller
    {
        // GET: Delivery
        public ActionResult Index()
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("getCurrentDeliveries").Result;
            IEnumerable<Delivery> livraisons = response.Content.ReadAsAsync<IEnumerable<Delivery>>().Result;
            ViewBag.livraisons = livraisons;

            response = Client.GetAsync("getTempsAttenteMoyen").Result;
            long attenteMoyen = response.Content.ReadAsAsync<long>().Result;
            attenteMoyen = attenteMoyen / 1000;
            int minutes = 0;
            int heures = 0;
            int jours = 0;
            while(attenteMoyen >= 60)
            {
                attenteMoyen -= 60;
                minutes++;
            }

            while(minutes>= 60)
            {
                minutes -= 60;
                heures++;
            }

            while (heures >= 24)
            {
                heures -= 24;
                jours++;
            }

            string attenteFinal = jours + " Jours, " + heures + " Heures, " + minutes + " Minutes, " + attenteMoyen + " Secondes";

            ViewBag.attenteMoyen = attenteFinal;


            response = Client.GetAsync("countHistoryDeliveries").Result;
            long countLivre = response.Content.ReadAsAsync<long>().Result;
            ViewBag.countLivre = countLivre;

            response = Client.GetAsync("getReclamationsByLivreur").Result;
            IEnumerable<ReclamationLivreurStat> statReclamation = response.Content.ReadAsAsync<IEnumerable<ReclamationLivreurStat>>().Result;
            //ViewBag.statReclamation = JsonConvert.SerializeObject(statReclamation);
            ViewBag.statReclamation = statReclamation;






            return View();
        }
    }
}