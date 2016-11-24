/**
 * boot app
 */

(function boot() {
  'use strict';

  // document ready
  document.addEventListener('DOMContentLoaded', main);

  function main() {
    angular.bootstrap(document, ['app']/*, {
      strictDi: true, // ng-strict-di=""
      cloak: true, // ng-cloak=""
    }*/);
  }
})();
