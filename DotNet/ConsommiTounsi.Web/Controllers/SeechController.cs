using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Speech.Recognition;

namespace ConsommiTounsi.Web.Controllers
{
    public class SeechController : Controller
    {

        SpeechRecognitionEngine recognitionEngine = new SpeechRecognitionEngine();

        // GET: Seech
        public ActionResult Index()
        {
            recognitionEngine.SpeechRecognized += new EventHandler<SpeechRecognizedEventArgs>(recognitionEngine_SpeechRecognized);
            LoadGrammar();
            recognitionEngine.SetInputToDefaultAudioDevice();
            recognitionEngine.RecognizeAsync(RecognizeMode.Multiple);
            return View();
        }

        private void LoadGrammar()
        {
            Choices commands = new Choices();
            commands.Add("blue");
            commands.Add("red");
            commands.Add("green");
            GrammarBuilder g_builder = new GrammarBuilder(commands);
            Grammar grammar = new Grammar(g_builder);
            recognitionEngine.LoadGrammar(grammar);

        }

        private void recognitionEngine_SpeechRecognized(object sender, SpeechRecognizedEventArgs e)
        {
            switch (e.Result.Text)
            {
                case "blue":
                    break;
            }
        }



        private void button1_Click(object sender, EventArgs e)
        {
            SpeechRecognitionEngine recognizer = new SpeechRecognitionEngine();
            Grammar dictationGrammar = new DictationGrammar();
            recognizer.LoadGrammar(dictationGrammar);
            try
            {
                ViewBag.Text = "Speak Now";
                recognizer.SetInputToDefaultAudioDevice();
                RecognitionResult result = recognizer.Recognize();
                ViewBag.Text = result.Text;
            }
            catch (InvalidOperationException exception)
            {
                ViewBag.Text = String.Format("Could not recognize input from default aduio device. Is a microphone or sound card available?\r\n{0} - {1}.", exception.Source, exception.Message);
            }
            finally
            {
                recognizer.UnloadAllGrammars();
            }
        }






    }
}