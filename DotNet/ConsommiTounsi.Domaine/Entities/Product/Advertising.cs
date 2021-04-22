using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.Entities.Wided
{
    public enum SponsorType
    {
        SOCIETY, ENTREPRENEUR
    }
    public class Advertising
    {
        //Proprietés de Base
        public int idAd { get; set; }
        public string channelAd { get; set; }
        public SponsorType sponsorType { get; set; }
        //Date
        public DateTime dateBeginAd { get; set; }
        public DateTime dateEndAd { get; set; }
        public int nbrManViewsAd { get; set; }
        public int nbrWomenViewsAd { get; set; }
        public float priceAdPerDay { get; set; }
        public float priceAdPerView { get; set; }

        //Proprietés de Navigation
        public Product product { get; set; }

    }
}
