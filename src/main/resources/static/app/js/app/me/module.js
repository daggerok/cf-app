/**
 * me module
 */

(function me() {
  'use strict';

  // ready
  document.addEventListener('DOMContentLoaded', main);

  function main() {

    const app = angular.module('app');

    /**
     * service
     */

    const PrincipalModel = function PrincipalModel($http, $log) {
      return {
        getPrincipal: getPrincipal
      };

      function getPrincipal() {
        return $http.get('/me').then(function principal(response) {
          return response.data || {name: 'guest'};
        }, $log.error);
      }
    };

    PrincipalModel.$inject = ['$http', '$log'];
    app.service('PrincipalModel', PrincipalModel);

    /**
     * controller
     */

    const MeController = function MeController(PrincipalModel) {

      this.principal = { name: 'N/A' };
      this.authenticated = function authenticated() {
        return 'guest' != this.principal.name;
      };

      setPrincipal(this);
      function setPrincipal($ctrl) {
        return PrincipalModel.getPrincipal().then(function getPrincipal(principal) {
          return $ctrl.principal = principal;
        });
      }
    };

    MeController.$inject = ['PrincipalModel'];

    /**
     * component
     */

    const meComponent = {
      controller: MeController,
      templateUrl: 'app/js/app/me/template.html',
    };

    app.component('me', meComponent);
  }
})();
