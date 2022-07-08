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
    const bundleCount = document.getElementById('bundleCount').value;

    for (let bundleIndex = 0; bundleIndex < bundleCount; bundleIndex++)
        UpdatePlusMinusButtonStates(bundleIndex);
}

function SetCountOrderedItems(quantityElement)
{
    const newQuantity = quantityElement.value;
    const clampedQuantity = GetClampedProductQuantity(newQuantity, minProducts, maxProducts);

    SetHiddenFieldFormProductsQuantity(quantityElement, clampedQuantity);
    UpdatePlusMinusButtonStates(clampedQuantity);
}

function OnPlusMinusButton(addedValue, changeQuantityButton)
{
    const bundleProductId = changeQuantityButton.value;
    const bundleIndex = GetBundleIndexByProductId(bundleProductId);
    const quantityElement = GetElementByBundleIndex(bundleIndex, 'quantityField');

    const newQuantity = parseInt(quantityElement.value) + parseInt(addedValue);
    const clampedQuantity = GetClampedProductQuantity(newQuantity, minProducts, maxProducts);

    SetFieldProductsQuantity(bundleIndex, clampedQuantity)
    SetHiddenFieldFormProductsQuantity(quantityElement, clampedQuantity);
    UpdatePlusMinusButtonStates(bundleIndex);

    SubmitTheFormWithNewCountInSec(bundleIndex, timeBeforeSubmittingForm);
}

function GetClampedProductQuantity(num, min, max)
{
    return Math.min(Math.max(num, min), max) ;
}

function UpdatePlusMinusButtonStates(bundleIndex)
{
    const quantityField = GetElementByBundleIndex(bundleIndex, 'quantityField');
    const minusButton = GetElementByBundleIndex(bundleIndex, 'minusButton');
    const plusButton = GetElementByBundleIndex(bundleIndex, 'plusButton');

    if(quantityField.value < minProducts + 1) minusButton.disabled = true;
    else if (quantityField.value > maxProducts - 1) plusButton.disabled = true;
    else
    {
        minusButton.disabled = false;
        plusButton.disabled = false;
    }
}

function SetFieldProductsQuantity(bundleIndex, newQuantity)
{
    const quantityField = GetElementByBundleIndex(bundleIndex, 'quantityField');
    quantityField.value = newQuantity;
}

function SetHiddenFieldFormProductsQuantity(hiddenQuantityField, newQuantity)
{
    hiddenQuantityField.value = newQuantity;
}

function SubmitTheFormWithNewCountInSec(bundleIndex, delayInSec)
{
    const millSecInSec = 1000;
    clearTimeout(lastTimerId);

    lastTimerId = setTimeout(() =>
    {
        const quantityField = GetElementByBundleIndex(bundleIndex, 'quantityField');
        const hiddenFormQuantityEl = GetElementByBundleIndex(bundleIndex, 'quantity');
        const formEl = GetElementByBundleIndex(bundleIndex, 'productPlusMinusForm');

        hiddenFormQuantityEl.value = quantityField.value;
        formEl.submit();

    }, delayInSec * millSecInSec);
}

function GetElementByBundleIndex(bundleIndex, elementName)
{
    const foundElements = document.getElementsByName(elementName);
    return foundElements[bundleIndex];
}

function GetBundleIndexByProductId(productId)
{
    const bundleCount = document.getElementById('bundleCount').value;

    for (let i = 0; i < bundleCount; i++)
    {
        let currentProductId = document.getElementsByName('productId')[i].value;
        if (currentProductId === productId) return i;
    }
}
