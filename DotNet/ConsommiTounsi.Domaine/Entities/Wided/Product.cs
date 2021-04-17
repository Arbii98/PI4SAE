using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.Entities.Wided
{
    public enum GenderRecommandation
    {
        WOMAN, MAN
    }
    public enum AgeRecommandation
    {
        CHILD, JUNIOR, SENIOR
    }
    public class Product
    {
        //Proprietés de Base
        public int ProductId { get; set; }
        public string nameProduct { get; set; }
        public float priceProduct { get; set; }
        //Date
        public DateTime dateCreationProduct { get; set; }
        public DateTime dateEndNewProduct { get; set; }
        public string imageFileNameProduct { get; set; }
        public string imageBarcodeFileNameProduct { get; set; }
        public string barcodeProduct { get; set; }
        public string qrCodeImageProduct { get; set; }
        public GenderRecommandation gender { get; set; }
        public AgeRecommandation age { get; set; }

        //Proprietés de Navigation
        public Category category { get; set; }
        public Marque marque { get; set; }
        public virtual ICollection<Advertising> advertisings { get; set; }

    }
}