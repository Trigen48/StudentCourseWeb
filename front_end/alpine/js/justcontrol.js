

var funcCall = null;
function showMessage(Text, Title = "Message", funcToCall = null)
{
    var e = $("#web_dialog");
    var f = $("#web_dialog_title");
    var g = $("#web_dialog_message");

  
    if (e != null && f != null && g != null)
    {
        f.html(Title);
        g.html(Text);
        e.show();
        funcCall = funcToCall;
    }

}

function closeMessage(id)
{
   
    if (id != null)
    {
        $("#" + id).hide();

        if (funcCall != null)
        {
            try
            {
                funcCall();
            }
            catch (e)
            {

            }
        }
    }
}

$(".sc-web-close").click(function ()
{
    var id = $(this).attr("winid");
    closeMessage(id);

});