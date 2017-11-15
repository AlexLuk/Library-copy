var statusField = $('#status_message');
var contentField = $('.content_result');

function getFormArray(elem, msgField) {
    // serializing all elements of the form
    var form_data = elem.closest('form').serializeArray();

    // adding button to the array of form fields
    form_data.push({name: elem.attr('name')});

    if (has_empty_fields(form_data, msgField))
        return '';
    return form_data;
}

function has_empty_fields(data, msgField) {
    var res = false;
    $.each(data, function (i, field) {
        if (field.value === '' && field.type !== 'submit') {
            show_alert("Please fill in all the fields!", msgField, false);
            res = true;
            return false;
        }
    });
    return res;
}

function show_alert(msg, msgField, isSuccess) {
    if (isSuccess) {
        msgField.removeClass('bg-danger').addClass('bg-success');
    }
    else {
        msgField.removeClass('bg-success').addClass('bg-danger');
    }
    msgField.toggle(true).text(msg);
}

function showResponse(resp, msgField) {
    if (resp === "false")
        show_alert("Unsuccessful attempt. Please try once again.", msgField, false);
    else
        show_alert(resp, msgField, true);
}

function hideMsgs() {
    statusField.toggle(false);
    contentField.toggle(false);
}

function prepareContentField() {
    contentField.toggle(true);

    var contentBody = contentField.find('tbody');
    contentBody.empty();
    return contentBody;
}

function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}


function addId(obj, attrName, id) {
    obj.attr(attrName, (obj.attr(attrName) + '_' + id));
}

function getId(attrName) {
    var parts = attrName.split("_");
    if (parts.length > 1) {
        return parts[1];
    }
    return "";
}

function reloadWithDelay(delay) {
    setTimeout(location.reload.bind(location), delay);
}

// http://totalprogus.blogspot.ru/2013/12/bootstrap-add-active-class-to-li.html
$('a[href="' + this.location.pathname + '"]').parents('li,ul').addClass('active');

// hides tooltips after closing the registration form
$('.close').click(function () {
    $('.tooltip').hide();
});

