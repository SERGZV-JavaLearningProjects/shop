function TogglePopup()
{
    const openModal = document.getElementById("openModalValue");
    const openValue = (openModal.value === 'true');
    const popup = document.getElementById("modalWindow");

    if (openValue) popup.classList.add('open');
    else popup.classList.remove('open');
}

function PopupCloseById(id)
{
    const popup = document.getElementById(id);
    popup.classList.remove('open');
}