import React, { createContext, useContext, useReducer, useEffect } from 'react'

const CartContext = createContext()
const initial = { items: [] }

function reducer(state, action){
  switch(action.type){
    case 'ADD': {
      const exists = state.items.find(i => i.id === action.product.id)
      if(exists){
        return { items: state.items.map(i => i.id === action.product.id ? {...i, quantity: i.quantity + 1 } : i) }
      }
      return { items: [...state.items, {...action.product, quantity: 1}] }
    }
    case 'REMOVE':
      return { items: state.items.map(i => i.id === action.id ? {...i, quantity: i.quantity - 1} : i).filter(i => i.quantity > 0) }
    case 'DELETE':
      return { items: state.items.filter(i => i.id !== action.id) }
    case 'CLEAR':
      return { items: [] }
    case 'SET':
      return { items: action.items }
    default: return state
  }
}

export function CartProvider({ children }){
  const [state, dispatch] = useReducer(reducer, initial, () => {
    try { return JSON.parse(localStorage.getItem('cart_v1')) || initial } catch { return initial }
  })

  useEffect(()=> localStorage.setItem('cart_v1', JSON.stringify(state)), [state])

  const addToCart = (product) => dispatch({ type: 'ADD', product })
  const removeFromCart = (id) => dispatch({ type: 'REMOVE', id })
  const deleteFromCart = (id) => dispatch({ type: 'DELETE', id })
  const clearCart = () => dispatch({ type: 'CLEAR' })

  const items = state.items
  const quantity = items.reduce((s,i)=> s + i.quantity, 0)
  const total = items.reduce((s,i)=> s + i.quantity * i.price, 0)

  return (
    <CartContext.Provider value={{ items, quantity, total, addToCart, removeFromCart, deleteFromCart, clearCart }}>
      {children}
    </CartContext.Provider>
  )
}

export const useCart = () => useContext(CartContext)
