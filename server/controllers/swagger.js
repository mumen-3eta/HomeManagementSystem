const swaggerJsDoc = require('swagger-jsdoc');
const swaggerUi = require('swagger-ui-express');

const swaggerOptions = {
  swaggerDefinition: {
    info: {
      version: '1.0.0',
      title: 'HMSy API',
      description: 'HMSy API Information',
      contact: {
        name: 'The Void ツ. Developer',
      },
      servers: ['http://localhost:3000'],
    },
  },
  apis: ['./routes/*/*.js'],
};

const swaggerDocs = swaggerJsDoc(swaggerOptions);

module.exports = {
  swaggerServe: swaggerUi.serve,
  swaggerSetup: swaggerUi.setup(swaggerDocs),
};
