let timer = 20;
let timerId;

function countdown()
{
    document.getElementById("_timer").innerHTML = timer.toString();
    timerId = setInterval(function ()
    {
        timer--;
        document.getElementById("_timer").innerHTML = timer.toString();

        if(timer < 1)
        {
            clearInterval(timerId);
            document.getElementById("redirect_to_profile").click();
        }
    }, 1000);
}