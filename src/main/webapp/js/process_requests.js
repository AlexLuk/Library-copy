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
    $('.doNotProcess').submit(function (e) {
        e.preventDefault();
    });

    $('button').click(function () {
        hideMsgs();
    });

    // hide error message when switching between tabs
    $('.navbar-nav a, #navbar').click(function () {
        hideMsgs();
    });

    $.ajaxSetup(
    {
        type: "POST"
    });

    /********************************************* registration form **************************************************/

    $('#registerForm').validate({
        rules: {
            firstName: "required",
            lastName: "required",
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                pwdcheck: true,
                minlength: 8
            }
        },
        messages: {
            firstName: $('#error_firstname').val(),
            lastName: $('#error_lastname').val(),
            password: {
                required: $('#error_password').val(),
                pwdcheck: $('#error_pwd_check').val(),
                minlength: $('#error_pwd_minlen').val()

            },
            email: {
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
        if($('#registerForm').valid()) {
            if (checkPassword()) {
                if (provideRegistration()) {
                    location.href = "account";
                }
            }
        }
    });

    $('#email').blur(function () {
        var userEmail = $.trim($('#email').val());
        if (isEmail(userEmail)) {
            $.ajax(
                {
                    url: "/checks/email",
                    data: {email: userEmail},
                    success: function (resp) {
                        if (!resp) alert("Email is already in the database. Choose another email, please");
                    }
                });
        }
    });

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    function checkPassword() {
        var userEmail = $.trim($('#email').val());
        var userPas = $.trim($('#password').val());
        var result = false;
        if (userEmail !== '' && userPas !== '') {
            $.ajax(
                {
                    url: "/checks/password",
                    data: {password: userPas, email: userEmail},
                    async: false,
                    success: function (resp) {
                        if (!resp) alert("Password contains part of the email. Choose another password, please");
                        result = resp;
                    }
                });
            return result;
        }
        else
            return false;
    }

    function provideRegistration() {
        var result = false;
        var form_data = $('#registerForm').serialize();
        if (form_data !== '') {
            $.ajax(
                {
                    
                    url: "/register",
                    data: form_data,
                    async: false,
                    success: function (resp) {
                        if (resp) alert("Thank you for registration! You were successfully authorized");
                        result = resp;
                    }
                });
            return result;
        }
        else
            return false;
    }
});

/************************* book filters *****************************************/
$('#filter_form').click(function () {
    filterRequest();
});

function filterRequest() {
    var titleFilter = $.trim($('#book_title').val());
    var authorFilter = $.trim($('#book_author').val());
    var yearFilter = $.trim($('#book_year').val());
    var genreFilter = $('#book_genre').val();
    if (titleFilter !== '' || authorFilter !== '' || yearFilter !== '' || genreFilter !== '') {
        $.ajax(
            {
                url: "/filters",
                data: {title: titleFilter, author: authorFilter, year: yearFilter, genre: genreFilter},
                dataType: "json",
                success: function (resp) {


                    $('#table_body').remove();

                    console.log("resp  " + JSON.stringify(resp));
                    $.each(resp, function (key, data) {
                        console.log(key)
                        $.each(data, function (index, data) {
                            console.log(index, data)
                        })
                    });

                    $.each(resp, function (key, data) {
                        $.each(data, function (index, item) {
                            $('.tab-content').append(
                                '<tr><td>'+item.title+'</td>' +
                                '<td>'+item.id+'</td>' +
                                '<td>'+item.year+'</td>' +
                                '<td>'+item.genre.name+'</td></tr>');
                        });
                    });
                }
            });
    }
}
