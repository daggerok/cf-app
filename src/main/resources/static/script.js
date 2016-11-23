(function app() {
    'use strict';

    // document ready
    document.addEventListener('DOMContentLoaded', main);

    function main() {

        const app = angular.module('app', []);

        // boot app
        angular.bootstrap(document, [app.name]);
    }

})();
