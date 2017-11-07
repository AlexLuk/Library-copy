$(document).ready(function () {
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
            //alert( field.name + ' ' + field.value + ' ' + field.type );
            if (field.value == '' && field.type != 'submit') {
                show_alert("Please fill in all the fields!", msgField, false);
                res = true;
                return false;
            }
        });
        return res;
    }

    function show_alert(msg, msgField, isSuccess) {
        if (isSuccess)
            msgField.removeClass('bg-danger').addClass('bg-success');
        else
            msgField.removeClass('bg-success').addClass('bg-danger');
        msgField.toggle(true).text(msg);
    }

    function showResponse(resp, msgField) {
        if (resp == "false")
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

    /************************************************* general settings *************************************************/

    // stop submit requests
    $( '#registerForm' ).submit( function( e )
    {
        e.preventDefault();
    });

    $( 'button' ).click( function()
    {
        hideMsgs();
    });

    // hide error message when switching between tabs
    $( '.navbar-nav a, #navbar' ).click( function()
    {
        hideMsgs();
    });

    $.ajaxSetup(
        {
            type: "POST"
        });

    /********************************************* registration form **************************************************/

    var form = $('#registerForm');
    form.validate({
        rules: {
            surname: "required",
            name: "required",
            email_register: {
                required: true,
                email: true
            },
            passwd_register: {
                required: true,
                pwdcheck: true,
                minlength: 8
            }
        },
        messages: {
            name: $('#error_name').val(),
            surname: $('#error_surname').val(),
            passwd_register: {
                required: $('#error_password').val(),
                pwdcheck: $('#error_pwd_check').val(),
                minlength: $('#error_pwd_minlen').val()

            },
            email_register: {
                required: $('#error_email_req').val(),
                email: $('#error_email').val()
            }
        }
    });

    $.validator.addMethod("pwdcheck",
        function (value, element) {
            return /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?.,/@#$%^&+=])(?=\S+$).{8,}$/.test(value);
        });

    $('#register').click(function () {
        if (checkPassword()) {
            provideRegistration();
        }
    });

    $('#email_register').blur(function () {
        // if (('#email_register').valid()) {
        var userEmail = $.trim($('#email_register').val());
        alert(userEmail);
        if (userEmail != '') {
            alert(userEmail);
            $.ajax(
                {
                    url: "/checks/email",
                    data: userEmail,
                    type: "POST",
                    success: function (resp) {
                        alert(resp);
                        return resp == "true";
                    }
                });
        }
        else
            return false;
        // }
    });

    function checkPassword() {
        var userEmail = $.trim($('#email_register').val());
        var userPas = $.trim($('#passwd_register').val());
        if (userEmail != '' && userPas!='') {
            alert("ajax checkPassword");
            $.ajax(
                {
                    url: "/checks/password",
                    data: {password: userPas, email: userEmail},
                    success: function (resp) {
                        alert(resp);
                        return resp == "true";
                    }
                });
        }
        else
            return false;
    }

    function provideRegistration() {
        var form_data = getFormArray($(this), statusField);
        alert(form_data);
        if (form_data != '') {
            alert("if");
            $.ajax(
                {
                    url: "/register",
                    data: form_data,
                    success: function (resp) {
                        alert(resp);
                        return resp == "true";
                    }
                });
        }
        else
            return false;
    }
});

/************************* book filters *****************************************/
$('#book_title').blur(function () {
    filterRequest();
});
$('#book_author').blur(function () {
    filterRequest();
});

$('#book_year').blur(function () {
    filterRequest();
});

$('#book_genre').blur(function () {
    filterRequest();
});

function filterRequest() {
    var titleFilter = $.trim($('#book_title').val());
    var authorFilter = $.trim($('#book_author').val());
    var yearFilter = $.trim($('#book_year').val());
    var genreFilter = $('#book_genre').val();
    if (!(titleFilter && authorFilter && yearFilter && genreFilter)) {
        $.ajax(
            {
                url: "/filters",
                data: {title: titleFilter, author: authorFilter, year: yearFilter, genre: genreFilter},
                success: function (resp) {
                    $('#nav-find').remove();
                    $.each(resp, function (index, item) {
                        $('.tab-content').append(
                            '<tr><td>item.bookTitle</td>' +
                            '<td>item.authorTitle<br/></td>' +
                            '<td>item.bookYear</td>' +
                            '<td>item.bookGenre</td></tr>');
                    });
                }
            });
    }
}
