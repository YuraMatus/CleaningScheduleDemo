(() => {
    const fetchUsers = () => fetch('/api/users/')
        .then(r => r.json())
        .then(data => data.users);

    const onSubmitCallback = user => () => fetch(
        '/api/records/',
        {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({user: user.id})
        }
    ).then(fillTable);

    const onDeleteCallback = record => () => fetch(
        `/api/records/${record.id}/`,
        {method: 'delete'}
    ).then(fillTable);

    const confirmCallback = callback => () => $.confirm({
        title: 'Are you sure?',
        content: 'Do you really want to do this?',
        buttons: {
            confirm() {
                callback()
                    .then(() => $.alert({
                        content: 'Success',
                        autoClose: 'ok|1000',
                    }))
            },
            cancel() {
            },
        }
    });

    const createTableCell = user => {
        let td, callback;
        if (user.records.length > 0) {
            let record = user.records.shift();
            td = $(`<td class="text-light bg-success">${new Date(record.date).toLocaleString()}</td>`);
            callback = onDeleteCallback(record);
        } else {
            td = $('<td/>');
            callback = onSubmitCallback(user);
        }

        td.on('click', confirmCallback(callback));
        return td;
    };

    let fillTable = async () => {
        let users = await fetchUsers();

        $('table thead > tr')
            .empty()
            .append(...users.map(u => $(`<th>${u.username}</th>`)));

        let longestRecords = Math.max(...users.map(u => u.records.length)) + 1;
        let tbody = $('table tbody').empty();

        for (let i = 0; i < longestRecords; i++) {
            tbody.append($(`<tr/>`).append(...users.map(createTableCell)));
        }
    };

    $(async () => {
        jconfirm.defaults = {
            title: null,
            theme: 'supervan',
            animation: 'zoom',
            closeAnimation: 'zoom',
            animationBounce: 2,
        };
        await fillTable();
    });
})();

