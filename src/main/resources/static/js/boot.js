/**
 * boot app
 */
(function boot() {
  'use strict';

  // document ready
  document.addEventListener('DOMContentLoaded', main);

  function main() {
    angular.bootstrap(document, [application.config.name], {
      strictDi: true, // instead of data-ng-strict-di=""
      cloak: true, // instead of data-ng-cloak=""
    });
  }

})();
