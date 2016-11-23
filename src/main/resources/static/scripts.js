(function app() {
  'use strict';

  // document ready
  document.addEventListener('DOMContentLoaded', main);

  function main() {

    /**
     * declare
     */

    const app = angular.module('app', []);

    /**
     * services
     */

    const EnvModel = function EnvModel($http, $log) {
      return {
        getEnv: getEnv
      };

      function getEnv() {
        return $http.get('/env').then(function env(payload, status, headers, config) {
          $log.info(payload.headers('X-Application-Context'));
          return payload.data || {};
        }, $log.error);
      }
    };
    EnvModel.$inject = ['$http', '$log'];
    app.service('EnvModel', EnvModel);

    /**
     * components
     */

    // env
    const envTemplate = `
      <div>
        {{ $ctrl.env }}
      </div>
    `;

    const EnvController = function EnvController(EnvModel) {
      this.env = {};
      setEnv(this);

      function setEnv($ctrl) {
        return EnvModel.getEnv().then(function getEnv(env) {
          return $ctrl.env = env;
        })
      }
    };
    EnvController.$inject = ['EnvModel'];

    const envComponent = {
      controller: EnvController,
      template: envTemplate,
    };

    app.component('env', envComponent);

    /**
     * boot app
     */

    angular.bootstrap(document, [app.name], {
      strictDi: true, // instead of data-ng-strict-di=""
      cloak: true, // instead of data-ng-cloak=""
    });
  }

})();
