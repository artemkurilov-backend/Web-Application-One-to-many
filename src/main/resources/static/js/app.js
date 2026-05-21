document.addEventListener("DOMContentLoaded", function () {

    const toastIds = [
        'successToast',
        'successBookToast',
        'deleteToastS',
        'deleteToastB',
        'editToast',
        'editStudentToast',
        'giveToast',
        'returnToast',
        'noResultsToast',
        'noResultsPageToast'
    ];

    toastIds.forEach(id => {

        const toastEl = document.getElementById(id);

        if (toastEl) {

            const toast = new bootstrap.Toast(toastEl, {
                delay: 3000
            });

            toast.show();
        }

    });

});