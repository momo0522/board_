
import './App.css';
import {MainPage} from './page/MainPage'
import {BoardPage} from './page/BoardPage'
import {BoardWritePage} from './page/BoardWritePage'
import {BoardUpdatePage} from './page/BoardUpdatePage'
import { Routes, Route } from 'react-router-dom'

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/:id" element={<BoardPage />} />
      <Route path="/write" element={<BoardWritePage />} />
      <Route path="/update/:id" element={<BoardUpdatePage />} />
    </Routes>
  );
}

export default App;
