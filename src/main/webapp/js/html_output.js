function filterOutput(resp) {
    var contentBody = $('.content_res_book');
    contentBody.empty();

    $.each(resp, function (key, data) {
        var onHands = $('#orderOnHandsForm').clone();
        var inLib = $('#orderInLibForm').clone();

        var htmlContent = '';
        htmlContent +=
            '<tr><td>' + data.title + '</td>' +
            '<td>' + data.authors + '</td>' +
            '<td>' + data.year + '</td>' +
            '<td>' + data.genre + '</td>';

        var button = onHands.find('button');
        addId(button, 'name', data.book_id);
        addId(button, 'id', data.book_id);

        button = inLib.find('button');
        addId(button, 'name', data.book_id);
        addId(button, 'id', data.book_id);

        htmlContent += '<td>' + onHands.html() + '</td>';
        htmlContent += '<td>' + inLib.html() + '</td></tr>';

        contentBody.append($(htmlContent));
    });

}