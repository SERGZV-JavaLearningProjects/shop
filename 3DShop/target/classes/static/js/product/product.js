function setNewImage(element)
{
    let mainImage = document.getElementById("main_product_image");
    mainImage.src = element.getAttribute('src');
}