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

    // hide error message when switching between tabs
    $('.navbar-nav a, #navbar').click(function () {
        hideMsgs();
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
            name: "Please enter your name",
            surname: "Please enter your surname",
            passwd_register: {
                required: "Please provide a password",
                pwdcheck: "Password must consist from A-Z, a-z, 0-9 and !?.,/@#$%^&+=",
                minlength: "Your password must be at least 8 characters long"

            },
            email_register: {
                required: "Please provide an email",
                email: "Please enter a valid email address"
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
        //     alert("valid");
            var userEmail = $.trim($('#email_register').val());
            if (userEmail != '') {
                $.ajax(
                    {
                        url: "/checks/email",
                        data: userEmail,
                        success: function (resp) {
                            // if (resp == "false")
                            //     show_alert("User with this email is on site already!", statusField, false);
                            // else
                            //     return true;
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
        if (form_data != '') {
            $.ajax(
                {
                    url: "/checks/password",
                    data: {password: userPas, email: userEmail},
                    success: function (resp) {
                        return resp == "true";
                    }
                });
        }
        else
            return false;
    }

    function provideRegistration() {
        var form_data = getFormArray($(this), statusField);
        if (form_data != '') {
            $.ajax(
                {
                    url: "/",
                    data: form_data,
                    success: function (resp) {
                        return resp == "true";
                    }
                });
        }
        else
            return false;
    }
});

