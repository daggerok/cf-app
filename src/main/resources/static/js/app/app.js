const application = { config: { name: 'app' } };

/**
 * declare app module
 */
(function app() {
  'use strict';

  // document ready
  document.addEventListener('DOMContentLoaded', main);

  function main() {
    angular.module(application.config.name, []);
  }

})();
