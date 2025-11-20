import { useEffect, useRef } from 'react'

function usePolling(callback, interval = 5000){
  const cbRef = useRef(callback)
  useEffect(()=> { cbRef.current = callback }, [callback])

  useEffect(()=>{
    if(!interval || interval <= 0) return
    const id = setInterval(()=> { cbRef.current() }, interval)
    return () => clearInterval(id)
  }, [interval])
}

export default usePolling
