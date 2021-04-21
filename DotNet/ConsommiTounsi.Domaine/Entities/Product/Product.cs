using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.Entities.Product
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
        public int? ProductId { get; set; }

        [Display(Name = "Image")]
        public string imageFileNameProduct { get; set; }
        [Display(Name = "Name")]
        public string nameProduct { get; set; }
        [Display(Name = "Price")]
        public float priceProduct { get; set; }
        public DateTime dateCreationProduct { get; set; }
        public DateTime dateEndNewProduct { get; set; }
        
        [Display(Name = "Barcode image")]
        public string imageBarcodeFileNameProduct { get; set; }
        [Display(Name = "Barcode")]
        public string barcodeProduct { get; set; }
        [Display(Name = "QrCode")]
        public string qrCodeImageProduct { get; set; }
        [Display(Name = "Gender")]
        public GenderRecommandation gender { get; set; }
        [Display(Name = "Age")]
        public AgeRecommandation age { get; set; }

        //Proprietés de Navigation
        public Category category { get; set; }
        public Marque marque { get; set; }
        public virtual ICollection<Advertising> Advertisings { get; set; }

    }
}