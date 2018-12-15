$(document).ready(function ()
{
    let form = $("form#words");
    let textarea = form.find("textarea").detach();
    let button = $("button#copy_words");

    let timeout = 0;

    button.on("click", function ()
    {
        textarea.css("display", "block");
        form.append(textarea);
        textarea.select();
        document.execCommand("copy");
        textarea.css("display", "none");
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

    // for admin page
    let rejectButtons = $("button.reject");
    rejectButtons.on("click", function (event)
    {
        event.preventDefault();
        $(this).css("display", "none");
        $(this).prev().css("display", "none");
        $(this).next().css("display", "inline")./*attr("required", true).*/attr("autofocus", true);
        $(this).parent().find("input[name=accept]").val("0");
        $(this).next().next().css("display", "inline");
        $(this).prev().prev().attr("disabled", true);
        $(this).prev().prev().val($(this).prev().prev().attr("data-org-word"));
    });

    // ===== //
    let editButtons = $("button.edit");
    editButtons.on("click", function (event)
    {
        event.preventDefault();
        $(this).css("display", "none");
        $(this).prev().attr("disabled", false).attr("autofocus", true);
        $(this).next().css("display", "inline");
        $(this).parent().find("input[name=edit]").val("1");
        $(this).next().next().css("display", "none");
    });

    // ==== //
    let delButtons = $("input.delete");
    delButtons.on("click", function (event)
    {
        // "%u0105" => "ą",
        // "%u0107" => "ć",
        let result = confirm("Na pewno usun\u{0105}\u{0107}?");
        if (!result)
        {
            event.preventDefault();
        }
    });
});
