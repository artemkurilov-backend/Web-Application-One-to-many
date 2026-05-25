document.addEventListener("DOMContentLoaded", function () {

    const modalEl = document.querySelector('.custom-modal');
    const backdropEl = document.querySelector('.custom-backdrop');

    if (!modalEl || !backdropEl) {
        return;
    }

    requestAnimationFrame(() => {

        modalEl.classList.add('show');
        backdropEl.classList.add('show');

    });

    // CANCEL
    const cancelBtn =
        document.getElementById('cancelBtn');

    if (cancelBtn) {

        cancelBtn.addEventListener('click', function (e) {

            e.preventDefault();

            modalEl.classList.add('hide-animation');

            modalEl.classList.remove('show');

            backdropEl.classList.remove('show');

            setTimeout(() => {

                window.location.href =
                    "/StudentController/ListOfStudents";

            }, 250);

        });

    }

    // DELETE
    const deleteBtn =
        document.getElementById('deleteBtn');

    if (deleteBtn) {

        deleteBtn.addEventListener('click', function (e) {

            e.preventDefault();

            modalEl.classList.add('hide-animation');

            modalEl.classList.remove('show');

            backdropEl.classList.remove('show');

            const deleteUrl =
                deleteBtn.getAttribute('href');

            setTimeout(() => {

                window.location.href =
                    deleteUrl;

            }, 250);

        });

    }

});