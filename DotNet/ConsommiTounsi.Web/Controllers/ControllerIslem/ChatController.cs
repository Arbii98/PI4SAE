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
using System.Web.UI.WebControls;

namespace PI4SAE5.Controllers.ControllerIslem
{
    public class ChatController : Controller
    {
        HttpClient httpClient;
        string baseAddress;
        User userc, userch;
        // GET: Chat
        public ActionResult Index(string idreciver)
        {
            // userc = Session["userConnected"] as User;
            userc = new User(1 ,"Islem","");
            userch = new User(System.Convert.ToInt32(idreciver),"","");
            Session["userConnected"] = userc;
            Session["userchat"] = userch;


            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage responce = Client.GetAsync("user/getalluser").Result;
         
            if (responce.IsSuccessStatusCode)
            {
                IEnumerable<User> lstuser = responce.Content.ReadAsAsync<IEnumerable<User>>().Result;
                ViewBag.contact = lstuser;
            }
            else
            {
                ViewBag.contact = "error";
            }
            ViewBag.result = getLastMsg();
            return View();
        }
        
        public string discussion()
        {
            userc = Session["userConnected"] as User;
            userch = Session["userchat"] as User;
            var chaineM = "";

            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage responce = Client.GetAsync("msgs/" + userc.id + "/" + userch.id).Result;

            if (responce.IsSuccessStatusCode)
            {
                IEnumerable<ConsommiTounsi.Domaine.entities.entities_Forum.Message> lstM  = responce.Content.ReadAsAsync<IEnumerable<Message>>().Result;
                foreach (Message msg in lstM)
                {
                    if (msg.sender.id == userc.id)
                    {
                        chaineM += "<div class='direct-chat-msg right'>"
                       + "<div class='direct-chat-info clearfix'>"
                       + "<span class='direct-chat-name pull-right'>" + msg.sender.name + " " + msg.sender.name + "</span>"
                       + "<span class='direct-chat-timestamp pull-left'>" + msg.date + "</span></div>"
                       + "<img class='direct-chat-img' src='data:image/png;base64," + msg.sender.image + "' alt='message user image'>"
                       + "<div class='direct-chat-text'>"
                       + msg.content + "</div></div>";                   
                    }
                
                else
                {
                    chaineM += "<div class='direct-chat-msg'><div class='direct-chat-info clearfix'><span class='direct-chat-name pull-left'>" +
                     msg.sender.name + " " + msg.sender.name +
                    "</span><span class='direct-chat-timestamp pull-right'>" +
                            msg.date +
                    "</span></div><img class='direct-chat-img' src='data:image/png;base64," + msg.sender.image + "' alt='message user image'>" +
                    "<div class='direct-chat-text'>" +
                     msg.content + "</div></div>";
                }
            }

            }
            ViewBag.result = getLastMsg();
            return chaineM;
        }

        public string envoiMessage(string message)
        {
            userc = Session["userConnected"] as User;
            userch = Session["userchat"] as User;


            HttpClient client = new HttpClient();
            HttpRequestMessage requestMessage = new HttpRequestMessage(HttpMethod.Post,
                "http://localhost:8081/SpringMVC/servlet/msgs");

            string json = new JavaScriptSerializer().Serialize(new
                {
                idsender= userc.id,
            idreciver = userch.id,
                content= message
    }
                );

            requestMessage.Content = new StringContent(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.SendAsync(requestMessage).GetAwaiter().GetResult();
            return discussion();
        }
    
        public IEnumerable<MessageDTO2> getLastMsg()
        {
            userc = Session["userConnected"] as User;
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:8081/SpringMVC/servlet/");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage responce = Client.GetAsync("msgs/" + userc.id).Result;
            return responce.Content.ReadAsAsync<IEnumerable<MessageDTO2>>().Result;

        }

    }
}