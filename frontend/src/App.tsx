import { Home } from "./components/Home";
import { Signin } from "./components/Signin";

export function App() {
  const isLogged = true;
  return isLogged ? <Home /> : <Signin />;
}
