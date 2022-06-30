const minProducts = 1;
const maxProducts = 999;

const timeBeforeSubmittingForm = 1;
let lastTimerId = "";

function InitCart()
{
    SetInitAddRemoveButtonsStates();
}

function SetInitAddRemoveButtonsStates()
{
    const quantityElement = document.getElementById('quantity');
    // SetNewProductsQuantity(quantityElement, quantityElement);
}

function SetCountOrderedItems(quantityElement)
{
    const newQuantity = quantityElement.value;
    const clampedQuantity = GetClampedProductQuantity(newQuantity, minProducts, maxProducts);

    UpdatePlusMinusButtonsStates(clampedQuantity);
    SetNewProductsQuantity(quantityElement, clampedQuantity);
}

function OnPlusMinusButton(addedValue)
{
    const quantityElement = document.getElementById('quantity');
    const newQuantity = parseInt(quantityElement.value) + parseInt(addedValue);
    const clampedQuantity = GetClampedProductQuantity(newQuantity, minProducts, maxProducts);

    UpdatePlusMinusButtonsStates(clampedQuantity);
    SetNewProductsQuantity(quantityElement, clampedQuantity);

    SubmitTheFormWithNewCountInSec(timeBeforeSubmittingForm);
}

function GetClampedProductQuantity(num, min, max)
{
    return Math.min(Math.max(num, min), max) ;
}

function UpdatePlusMinusButtonsStates(newQuantity)
{
    if(newQuantity < minProducts + 1) document.getElementById('minusButton').disabled = true;
    else if(newQuantity > maxProducts - 1) document.getElementById('plusButton').disabled = true;
    else
    {
        document.getElementById('minusButton').disabled = false;
        document.getElementById('plusButton').disabled = false;
    }
}

function SetNewProductsQuantity(quantityField, newQuantity)
{
    quantityField.value = newQuantity;
}

function SubmitTheFormWithNewCountInSec(delayInSec)
{
    const millSecInSec = 1000;
    clearTimeout(lastTimerId);

    lastTimerId = setTimeout(() =>
    {
        const buttonQuantityEl = document.getElementById('quantity');
        const formQuantityEl = document.getElementsByName('quantity')[0];
        formQuantityEl.value = buttonQuantityEl.value;

        document.getElementById('productPlusMinusForm').submit();

    }, delayInSec * millSecInSec);
}

