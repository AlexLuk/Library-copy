function filterOutput(resp) {
    var contentBody = $('.content_res_book');
    contentBody.empty();

    $.each(resp, function (key, data) {
        var onHands = $('.orderOnHandsForm').clone();
        var inLib = $('.orderInLibForm').clone();

        var htmlContent = $('<tr></tr>');
        htmlContent.append($('<td>' + data.title + '</td>'));
        htmlContent.append($('<td>' + data.authors + '</td>'));
        htmlContent.append($('<td>' + data.year + '</td>'));
        htmlContent.append($('<td>' + data.genre + '</td>'));

        var td = $('<td></td>');
        if (data.isRare) {
            td.append($('#msg_is_rare'));
        } else {
            td.append($('#msg_not_rare'));
        }
        htmlContent.append(td);
        htmlContent.append($('<td>' + data.amount + '</td>'));

        var button = onHands.find('button');
        addId(button, 'name', data.book_id);
        addId(button, 'id', data.book_id);

        button = inLib.find('button');
        addId(button, 'name', data.book_id);
        addId(button, 'id', data.book_id);

        htmlContent.append($('<td></td>').append(onHands));
        if (data.isRare) {
            $(htmlContent).find('.orderHands').attr('disabled', true);
        }
        htmlContent.append($('<td></td>').append(inLib));
        contentBody.append($(htmlContent));
    });
}
