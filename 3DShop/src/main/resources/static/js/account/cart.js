function TogglePopup(modalOpenState, modalToToggleId)
{
    const openValue = (modalOpenState === 'true');
    const popup = document.getElementById(modalToToggleId);

    if (openValue) popup.classList.add('open');
    else popup.classList.remove('open');
}

