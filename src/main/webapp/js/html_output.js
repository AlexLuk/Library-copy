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
        htmlContent.append($('<td>' + data.isRare + '</td>'));
        htmlContent.append($('<td>' + data.amount + '</td>'));

        var button = onHands.find('button');
        addId(button, 'name', data.book_id);
        addId(button, 'id', data.book_id);

        button = inLib.find('button');
        addId(button, 'name', data.book_id);
        addId(button, 'id', data.book_id);

        htmlContent.append($('<td>' + onHands.html() + '</td>'));
        $(htmlContent).find('.orderHands').attr('disabled', true);
        htmlContent.append($('<td>' + inLib.html() + '</td>'));

        contentBody.append($(htmlContent));
    });
}
