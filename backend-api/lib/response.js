const headers = {
  'Access-Control-Allow-Methods': '*',
  'Access-Control-Allow-Origin': '*',
  'Content-Type': 'application/json'
};

const build = (statusCode) => (body) => {
  return {
    headers,
    statusCode,
    body: body !== undefined ? JSON.stringify(body) : ''
  };
};

const created = build(201);
const success = build(200);
const noContent = build(204);
const badRequest = build(400);
const unauthorized = build(401);
const forbidden = build(403);
const notFound = build(404);
const failure = build(500);

module.exports = {
  created,
  success,
  noContent,
  failure,
  badRequest,
  unauthorized,
  forbidden,
  notFound
};
