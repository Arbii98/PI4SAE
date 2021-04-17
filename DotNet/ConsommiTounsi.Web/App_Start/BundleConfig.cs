using System.Web;
using System.Web.Optimization;

namespace ConsommiTounsi.Web
{

    
        public class BundleConfig
        {
            // For more information on bundling, visit http://go.microsoft.com/fwlink/?LinkId=301862
            public static void RegisterBundles(BundleCollection bundles)
            {
                bundles.Add(new ScriptBundle("~/bundles/jquery").Include(
                            "~/Scripts/jquery-{version}.js"));

                bundles.Add(new ScriptBundle("~/bundles/jqueryval").Include(
                            "~/Scripts/jquery.validate*"));

                // Use the development version of Modernizr to develop with and learn from. Then, when you're
                // ready for production, use the build tool at http://modernizr.com to pick only the tests you need.
                bundles.Add(new ScriptBundle("~/bundles/modernizr").Include(
                            "~/Scripts/modernizr-*"));

                bundles.Add(new ScriptBundle("~/bundles/bootstrap").Include(
                          "~/Scripts/bootstrap.js",
                          "~/Scripts/respond.js"));

                bundles.Add(new StyleBundle("~/Content/css").Include(
                          "~/Content/bootstrap.css",
                          "~/Content/site.css"));

                #region Template design
                bundles.Add(new StyleBundle("~/template/css").Include(
                          "~/Content/css/bootstrap.min.css",
                          "~/Content/css/slick.css",
                          "~/Content/css/slick-theme.css",
                          "~/Content/css/nouislider.min.css",
                          "~/Content/css/font-awesome.min.css",
                          "~/Content/css/style.css",
                         "~/Content/jss/css/style.css",
                      "~/Content/lib/slick/slick-theme.css",
                       "~/Content/lib/slick/slick.css",
                       "~/Content/lib/slick/slick-theme.less",
                       "~/Content/lib/slick/slick.less",
                       "~/Content/css/AdminLTE.min.css",
                       "~/Content/bootstrap/dist/css/bootstrap.min.css",
                        "~/Content/font-awesome/css/font-awesome.min.css",
                        "~/Content/lib/slick/fonts/slick.ttf"

                      ));
            var styleBundle = new StyleBundle("~/Content/Bundled")
    .Include("~/Content/Bundled.css");

            BundleTable.Bundles.Add(styleBundle);


            bundles.Add(new StyleBundle("~/template/csss").Include(


                        "~/Content/bootstrap/dist/css/bootstrap.min.css",
                        "~/Content/font-awesome/css/font-awesome.min.css",
                        "~/Content/css/AdminLTE.min.css"
                     ));
       

          

            bundles.Add(new ScriptBundle("~/template/script").Include(
                    "~/Content/js/jquery.min.js",
                    "~/Content/js/bootstrap.min.js",
                    "~/Content/js/slick.min.js",
                    "~/Content/js/nouislider.min.js",
                    "~/Content/js/jquery.zoom.min.js",
                    "~/Content/js/main.js",
                    "~/Content/jquery/jquery.min.js",
                    "~/Content/js/adminlte.min.js",
                    "~/Content/jss/js/main.js",
                    "~/Content/lib/slick/slick.js",
                         "~/Content/lib/slick/slick.min.js",
                         "~/Content/lib/easing/easing.js",
                          "~/Content/lib/easing/easing.min.js"
                    ));

                #endregion
            }
        }
    }
