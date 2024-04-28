// import { User } from "lucide-react";

export function Signin() {
  return (
    <div className="flex h-screen">
      <div
        className="hidden lg:block w-1/2 bg-cover bg-center"
        style={{ backgroundImage: "url('login_splash.jpg')" }}
      ></div>
      <div className="w-full lg:w-1/2 flex items-center justify-center px-4">
        <div className="max-w-md w-full">
          <h2 className="text-2xl font-bold mb-6">Login</h2>
          <form>
            <div className="mb-4">
              <label htmlFor="username" className="block mb-1">
                Usuario
              </label>
              <input
                type="text"
                id="username"
                name="username"
                placeholder="Digite seu usuário"
                required
                className="w-full px-3 py-2 border border-gray-300 rounded-md"
              />
            </div>
            <div className="mb-4">
              <label htmlFor="password" className="block mb-1">
                Senha
              </label>
              <input
                type="password"
                id="password"
                name="password"
                placeholder="Digite sua senha"
                required
                className="w-full px-3 py-2 border border-gray-300 rounded-md"
              />
            </div>
            <button
              type="submit"
              className="w-full bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 transition-colors duration-300"
            >
              Entrar
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
