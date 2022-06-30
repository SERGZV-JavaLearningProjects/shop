function ToggleWarningDeletePopup(modalOpenState, inputEl)
{
    const openValue = (modalOpenState === 'true');
    const popup = document.getElementById('deleteWarningModal');

    if (openValue)
    {
        popup.classList.add('open');
        SetSelectedProductId(inputEl);
    }
    else popup.classList.remove('open');
}

function SetSelectedProductId(inputEl)
{
    const productId = inputEl.value;
    const hiddenIdFields = document.getElementsByName('currentBundleProductId');
    hiddenIdFields[0].value = productId;
}