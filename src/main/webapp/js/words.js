$(document).ready(function ()
{
    let form = $("form#words");
    let textarea = form.find("textarea").detach();
    let button = $("button#copy_words");

    let timeout = 0;

    button.on("click", function ()
    {
        form.append(textarea);
        textarea.select();
        document.execCommand("copy");
        textarea.remove();

        $("a.game-link").slideDown(500);

        if (timeout === 0)
        {
            $("div.copied").fadeIn(500);
            $("div.copied").fadeOut(5000);
            timeout = 1;
            setTimeout(function ()
            {
                timeout = 0;
            }, 5500);
        }
    });
});
