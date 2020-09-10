export function isvalidPass (str) {
  const reg = /^[a-zA-Z]\w{8,18}$/
  return reg.test(str)
}
