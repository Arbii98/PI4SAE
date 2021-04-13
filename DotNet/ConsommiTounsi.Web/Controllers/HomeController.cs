using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ConsommiTounsi.Web.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            ViewBag.Title = "Home";

            return View();
        }

        public ActionResult AdminTemplate()
        {
            return View("~/Views/Shared/BackOffice.cshtml");
        }

        public ActionResult Dashboard()
        {
            return View("~/Views/Home/Dashboard.cshtml");
        }
    }
}
