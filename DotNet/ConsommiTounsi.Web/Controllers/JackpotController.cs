using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using ConsommiTounsi.Domaine.Entities;
using ConsommiTounsi.Domaine.Entities.Stat;
using System.Net.Http;


namespace ConsommiTounsi.Web.Controllers
{
    public class JackpotController : Controller
    {
        // GET: Jackpot
        public ActionResult Index()
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            
            HttpResponseMessage response = Client.GetAsync("getJackpotEvolution").Result;
            IEnumerable<JackpotEvolutionStat> jackpotEvolutionStat = response.Content.ReadAsAsync<IEnumerable<JackpotEvolutionStat>>().Result;
            ViewBag.jackpotEvolutionStat = jackpotEvolutionStat;

            response = Client.GetAsync("getCurrentMontantTotal").Result;
            float currentMontantTotal = response.Content.ReadAsAsync<float>().Result;
            ViewBag.currentMontantTotal = currentMontantTotal;

            response = Client.GetAsync("getMontantTotal").Result;
            float montantTotal = response.Content.ReadAsAsync<float>().Result;
            ViewBag.montantTotal = montantTotal;

            response = Client.GetAsync("getTopDonators").Result;
            IEnumerable<TopDonatorsStat> topDonatorsStat = response.Content.ReadAsAsync<IEnumerable<TopDonatorsStat>>().Result;
            ViewBag.topDonatorsStat = topDonatorsStat;

            return View();
        }
    }
}